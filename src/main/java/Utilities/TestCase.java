package Utilities;

import java.util.ArrayList;
import java.util.List;


public class TestCase {
    private String modul;
    private String name;
    private String status;
    private List<String> steps;

    // 2. Modify constructor to include 'modul'
    public TestCase(String modul,String name, String status, List<String> steps) {
        this.modul = modul;
        this.name = name;
        this.status = status;
        this.steps = new ArrayList<>(steps);
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public String getModul() {
        return modul;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getSteps() {
        return new ArrayList<>(steps); // Return a copy to prevent external modifications
    }

    public void setSteps(List<String> steps) {
        this.steps = new ArrayList<>(steps); // Make a copy to protect encapsulation
    }

    public String getFormattedSteps() {
        StringBuilder formattedSteps = new StringBuilder();
        for (int i = 0; i < steps.size(); i++) {
            formattedSteps.append(i + 1).append(". ").append(steps.get(i)).append("<br>");
        }
        return formattedSteps.toString();
    }

    // 4. Update 'toString()' to include 'modul'
    @Override
    public String toString() {
        return "TestCase{" +
                "name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", steps=" + steps +
                '}';
    }
}