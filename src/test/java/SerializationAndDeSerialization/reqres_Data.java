package SerializationAndDeSerialization;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true) // This will ignore unknown properties
public class reqres_Data {

    // User information fields
    private String name;
    private String job;
    private int age;

    // Nested class to match the "data" part of the response
    private Data data;

    // Getters and setters for the 'name', 'job', 'age', and 'data' fields
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

   
    // Nested class to match the "data" object in the response
    public static class Data {
        private int id;
        private String name;
        private int year;
        private String color;
        private String pantone_value;

        // Getters and setters for the 'data' fields
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getPantone_value() {
            return pantone_value;
        }

        public void setPantone_value(String pantone_value) {
            this.pantone_value = pantone_value;
        }
        // Method to return the user info as a string
        public String GetreqresData() {
            return (this.name + "  " + this.id + "  " + this.year+"  "+this.color);
        }

    }
}
