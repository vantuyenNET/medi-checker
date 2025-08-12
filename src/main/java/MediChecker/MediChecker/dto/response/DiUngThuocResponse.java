package MediChecker.MediChecker.dto.response;

import MediChecker.MediChecker.enumer.MucDoNghiemTrong;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DiUngThuocResponse {
    private Long id;
    private Long benhNhanId;
    private String tenBenhNhan;
    private ThuocResponse thuoc;
    private String trieuChung;
    private MucDoNghiemTrong mucDoNghiemTrong;
    private LocalDateTime ngayPhatHien;
}
