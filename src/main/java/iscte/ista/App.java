package iscte.ista;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class App {
    private static final String FIRSTNAMES_F_FILE = "data/firstnames_f.json";
    private static final String FIRSTNAMES_M_FILE = "data/firstnames_m.json";
    private static final String SURNAMES_FILE = "data/surnames.json";
    private static final String STUDENTS_CVS_FILE = "data/students.cvs";
    private static final int INITIAL_NUMBER = 21000;

    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<String>> listOfStrings = new TypeReference<List<String>>() { };

        // Nomes próprios (femininos e masculinos) e apelidos lidos dos ficheiros JSON
        List<String> firstNames = new ArrayList<>(mapper.readValue(new File(FIRSTNAMES_F_FILE), listOfStrings));
        firstNames.addAll(mapper.readValue(new File(FIRSTNAMES_M_FILE), listOfStrings));
        List<String> surnames = mapper.readValue(new File(SURNAMES_FILE), listOfStrings);

        Scanner in = new Scanner(System.in);
        System.out.print("Tamanho da lista de alunos: ");
        int n = in.nextInt();

        // Não é possível gerar mais nomes distintos do que combinações existentes
        long maxCombinations = (long) firstNames.size() * surnames.size();
        if (n > maxCombinations) {
            System.out.println("Máximo possível: " + maxCombinations);
            return;
        }

        Random random = new Random();
        Set<String> usedNames = new HashSet<>();
        List<Student> students = new ArrayList<>();
        int number = INITIAL_NUMBER;

        while (students.size() < n) {
            String name = firstNames.get(random.nextInt(firstNames.size()))
                    + " " + surnames.get(random.nextInt(surnames.size()));
            if (usedNames.add(name)) {   // add devolve false se o nome já existia
                students.add(new Student(name, number++));
            }
        }

        try (PrintWriter out = new PrintWriter(STUDENTS_CVS_FILE)) {
            out.println("number,name");
            for (Student s : students) {
                out.println(s.toCSV());
            }
        }
        System.out.println("Ficheiro gerado: " + STUDENTS_CVS_FILE);
    }
}