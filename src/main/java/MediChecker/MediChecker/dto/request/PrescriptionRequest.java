package MediChecker.MediChecker.dto.request;

import java.util.List;

public class PrescriptionRequest {
    private List<PrescriptionItem> prescription_list;
    private List<String> allergies;
    private List<String> medical_history;

    // Getters và setters
    public List<PrescriptionItem> getPrescription_list() { return prescription_list; }
    public void setPrescription_list(List<PrescriptionItem> prescription_list) { this.prescription_list = prescription_list; }

    public List<String> getAllergies() { return allergies; }
    public void setAllergies(List<String> allergies) { this.allergies = allergies; }

    public List<String> getMedical_history() { return medical_history; }
    public void setMedical_history(List<String> medical_history) { this.medical_history = medical_history; }
}
