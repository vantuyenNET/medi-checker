package MediChecker.MediChecker.service;

import MediChecker.MediChecker.dto.request.DiUngThuocRequest;
import MediChecker.MediChecker.dto.response.DiUngThuocResponse;
import MediChecker.MediChecker.dto.response.ThuocResponse;
import MediChecker.MediChecker.entity.BenhNhan;
import MediChecker.MediChecker.entity.DiUngThuoc;
import MediChecker.MediChecker.entity.Thuoc;
import MediChecker.MediChecker.repository.BenhNhanRepository;
import MediChecker.MediChecker.repository.DiUngThuocRepository;
import MediChecker.MediChecker.repository.ThuocRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiUngThuocService {

    private final DiUngThuocRepository diUngThuocRepository;
    private final BenhNhanRepository benhNhanRepository;
    private final ThuocRepository thuocRepository;

    public DiUngThuocService(DiUngThuocRepository diUngThuocRepository,
                             BenhNhanRepository benhNhanRepository,
                             ThuocRepository thuocRepository) {
        this.diUngThuocRepository = diUngThuocRepository;
        this.benhNhanRepository = benhNhanRepository;
        this.thuocRepository = thuocRepository;
    }

    public DiUngThuocResponse getById(Long id) {
        return toResponse(diUngThuocRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy dị ứng thuốc")));
    }

    public List<DiUngThuocResponse> getByBenhNhanId(Long benhNhanId) {
        return diUngThuocRepository.findByBenhNhanId(benhNhanId)
                .stream().map(this::toResponse)
                .collect(Collectors.toList());
    }

    public DiUngThuocResponse create(DiUngThuocRequest request) {
        BenhNhan benhNhan = benhNhanRepository.findById(request.getBenhNhanId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy bệnh nhân"));
        Thuoc thuoc = thuocRepository.findById(request.getThuocId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy thuốc"));

        DiUngThuoc entity = new DiUngThuoc();
        entity.setBenhNhan(benhNhan);
        entity.setThuoc(thuoc);
        entity.setTrieuChung(request.getTrieuChung());
        entity.setMucDoNghiemTrong(request.getMucDoNghiemTrong());

        return toResponse(diUngThuocRepository.save(entity));
    }

    public DiUngThuocResponse update(Long id, DiUngThuocRequest request) {
        DiUngThuoc existing = diUngThuocRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy dị ứng thuốc"));

        if (request.getThuocId() != null) {
            Thuoc thuoc = thuocRepository.findById(request.getThuocId())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy thuốc"));
            existing.setThuoc(thuoc);
        }
        if (request.getTrieuChung() != null) {
            existing.setTrieuChung(request.getTrieuChung());
        }
        if (request.getMucDoNghiemTrong() != null) {
            existing.setMucDoNghiemTrong(request.getMucDoNghiemTrong());
        }

        return toResponse(diUngThuocRepository.save(existing));
    }

    public void delete(Long id) {
        diUngThuocRepository.deleteById(id);
    }

    private DiUngThuocResponse toResponse(DiUngThuoc entity) {
        DiUngThuocResponse dto = new DiUngThuocResponse();
        dto.setId(entity.getId());

        Thuoc thuoc = entity.getThuoc();
        if (thuoc != null) {
            ThuocResponse thuocResponse = new ThuocResponse();
            thuocResponse.setId(thuoc.getId());
            thuocResponse.setTenThuoc(thuoc.getTenThuoc());
            dto.setThuoc(thuocResponse);
        }

        dto.setTrieuChung(entity.getTrieuChung());
        dto.setMucDoNghiemTrong(entity.getMucDoNghiemTrong());
        dto.setNgayPhatHien(entity.getNgayPhatHien());

        return dto;
    }

}
