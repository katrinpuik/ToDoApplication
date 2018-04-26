package dto;

public class ToDo {
    private String description;


    public ToDo(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description;
    }

    @Override
    public boolean equals (Object o) {
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
        return this.getDescription().equals(otherToDo.getDescription());
    }
}
