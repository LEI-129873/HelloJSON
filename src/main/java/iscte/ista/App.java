package iscte.ista;

import com.fasterxml.jackson.databind.ObjectMapper;

public class App
{
    public static void main( String[] args ) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        Student a =  new Student("Ana Silva", 123456);

        // Objeto para JSON
        String json = mapper.writeValueAsString(a);
        System.out.println("JSON: " + json);

        // JSON para Objeto
        Student s2 = mapper.readValue(json, Student.class);
        System.out.println("Objeto: " + s2);
    }
}
