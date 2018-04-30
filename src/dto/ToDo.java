package dto;


import enums.Status;

public class ToDo {
    private String description;
    private int id;
    private Status status;

    private static int ID_COUNTER;

    public ToDo(String description) {
        this.id = getNextId();
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    int getId() {
        return id;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public String toString() {
        if (status != null) {
            return description + ", " + status;
        } else {
            return description + ", status undefined";
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (getClass() != o.getClass()) {
            return false;
        }

        final ToDo otherToDo = (ToDo) o;
        return this.id == otherToDo.getId();
    }

    private int getNextId() {
        return ++ID_COUNTER;
    }
}
