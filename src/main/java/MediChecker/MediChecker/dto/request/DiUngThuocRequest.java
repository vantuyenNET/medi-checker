package MediChecker.MediChecker.dto.request;

import MediChecker.MediChecker.enumer.MucDoNghiemTrong;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DiUngThuocRequest {

    @NotNull(message = "Bệnh nhân ID không được để trống")
    private Long benhNhanId;

    @NotNull(message = "Thuốc ID không được để trống")
    private Long thuocId;

    @Size(max = 500, message = "Triệu chứng không được vượt quá 500 ký tự")
    @NotNull(message = "Triệu chứng không được để trống")
    private String trieuChung;

    @NotNull(message = "Mức độ nghiêm trọng không được để trống")
    private MucDoNghiemTrong mucDoNghiemTrong;
}
