package MediChecker.MediChecker.controller;

import MediChecker.MediChecker.dto.request.DiUngThuocRequest;
import MediChecker.MediChecker.dto.response.DiUngThuocResponse;
import MediChecker.MediChecker.service.DiUngThuocService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/di-ung-thuoc")
@Tag(name = "Quản lý dị ứng thuốc", description = "API quản lý thông tin dị ứng thuốc của bệnh nhân")
public class DiUngThuocController {

    private final DiUngThuocService diUngThuocService;

    public DiUngThuocController(DiUngThuocService diUngThuocService) {
        this.diUngThuocService = diUngThuocService;
    }

    @Operation(summary = "Xóa dị ứng thuốc của bệnh nhân")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        diUngThuocService.delete(id);
    }

    @Operation(summary = "Chi tiết dị ứng thuốc")
    @GetMapping("/{id}")
    public DiUngThuocResponse getById(@PathVariable Long id) {
        return diUngThuocService.getById(id);
    }

    @Operation(summary = "Danh sách dị ứng thuốc của bệnh nhân")
    @GetMapping("/benh-nhan/{benhNhanId}")
    public List<DiUngThuocResponse> getByBenhNhanId(@PathVariable Long benhNhanId) {
        return diUngThuocService.getByBenhNhanId(benhNhanId);
    }

    @PostMapping
    @Operation(summary = "Tạo thông tin dị ứng thuốc")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Thông tin dị ứng thuốc",
            required = true,
            content = @Content(
                    schema = @Schema(implementation = DiUngThuocRequest.class),
                    examples = @ExampleObject(value = """
                        {
                          "benhNhanId": 1,
                          "thuocId": 2,
                          "trieuChung": "Phát ban, khó thở",
                          "mucDoNghiemTrong": "[VUA, NHE, NANG, RAT_NANG]"
                        }
                        """)
            )
    )
    public DiUngThuocResponse create(@Valid @RequestBody DiUngThuocRequest request) {
        return diUngThuocService.create(request);
    }

    @Operation(summary = "Chỉnh sửa dị ứng thuốc của bệnh nhân")
    @PutMapping("/{id}")
    public DiUngThuocResponse update(
            @PathVariable Long id,
            @Valid @RequestBody DiUngThuocRequest request) {
        return diUngThuocService.update(id, request);
    }
}
