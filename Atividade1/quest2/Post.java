enum Status {
    DRAFT,
    PUBLISHED,
    DELETED
}

@Data
@AllArgsConstrutor
@NoAllArgsConstrutor

public class Post {
    private int id;
    private String text;
    private Status status;

    Post(int id, String text, Status status) {
        this.setId(id);
        this.setText(text);
        this.setStatus(status);
    }

    public void publish() {
		validate();

        this.setStatus(Status.PUBLISHED);
    }

	private void validate() {
        if (this.getStatus() != Status.DRAFT || this.getText().trim().length() == 0) {
            throw new RuntimeException("A post must have at least one character. Only drafts can be poste");
        }
    }
    
}