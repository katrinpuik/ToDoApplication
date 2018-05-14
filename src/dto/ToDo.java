package dto;


import enums.Status;

public class ToDo {
    private String description;
    private Status status;

    public ToDo(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return description + ", " + String.valueOf(status);
    }

}
