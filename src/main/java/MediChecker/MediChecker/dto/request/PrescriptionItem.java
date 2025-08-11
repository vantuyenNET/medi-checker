package MediChecker.MediChecker.dto.request;

public class PrescriptionItem {
    private String drug_name;
    private String dosage;
    private String frequency;

    // Getters và setters
    public String getDrug_name() { return drug_name; }
    public void setDrug_name(String drug_name) { this.drug_name = drug_name; }

    public String getDosage() { return dosage; }
    public void setDosage(String dosage) { this.dosage = dosage; }

    public String getFrequency() { return frequency; }
    public void setFrequency(String frequency) { this.frequency = frequency; }
}
