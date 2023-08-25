import io.kubernetes.client.spring.extended.manifests.annotation.FromYaml;
import io.kubernetes.client.util.Yaml;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.yaml.snakeyaml.error.YAMLException;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class KubernetesFromYamlProcessor implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {

    private static final Logger log = LoggerFactory.getLogger(KubernetesFromYamlProcessor.class);
    private ListableBeanFactory beanFactory;

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        try {
            // Tell: Instrua o objeto a realizar ação em vez de perguntar
            loadFromYamlIfNeeded(bean, beanName);
        } catch (Exception e) {
            // Fail first: Lidar com erros o mais cedo possível
            log.error("Erro ao processar bean {}.", beanName, e);
            throw new BeanCreationException("Erro ao processar bean " + beanName, e);
        }
        return bean;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (ListableBeanFactory) beanFactory;
    }

    private void loadFromYamlIfNeeded(Object bean, String beanName) throws NoSuchBeanDefinitionException {
        for (Field field : bean.getClass().getDeclaredFields()) {
            FromYaml fromYamlAnnotation = field.getAnnotation(FromYaml.class);
            if (fromYamlAnnotation != null) {
                if (!field.getType().isAssignableFrom(YamlLoadedType.class)) {
                    log.warn(
                            "Incompatible types for @FromYaml, {} cannot apply to {}",
                            fromYamlAnnotation.filePath(),
                            field.getType());
                } else {
                    // Tell: Instrua o objeto a realizar ação em vez de perguntar
                    loadAndSetFieldFromYaml(field, bean, fromYamlAnnotation.filePath());
                }
            }
        }
    }

    private void loadAndSetFieldFromYaml(Field field, Object bean, String targetFilePath) {
        // Fail first: Detecte erros o mais cedo possível
        if (field.get(bean) != null) {
            log.warn("Campo {} já está definido, pulando o carregamento do YAML.", field.getName());
            return;
        }

        Path targetPath = Paths.get(targetFilePath);
        if (!Files.exists(targetPath)) {
            // Fail first: Lançar exceção explícita em caso de erro
            throw new BeanCreationException(
                    "Arquivo não encontrado: " + targetFilePath);
        }

        try {
            String yamlContent = new String(Files.readAllBytes(targetPath));
            YamlLoadedType loadedObj = Yaml.loadAs(yamlContent, YamlLoadedType.class);
            field.set(bean, loadedObj);
        } catch (IOException | YAMLException | IllegalAccessException e) {
            // Fail first: Detecte erros o mais cedo possível e lide com eles
            log.error("Erro ao carregar e definir o campo {} a partir do YAML.", field.getName(), e);
            throw new BeanCreationException("Erro ao carregar e definir o campo a partir do YAML.", e);
        }
    }
}
