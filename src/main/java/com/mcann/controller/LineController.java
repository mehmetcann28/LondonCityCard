package com.mcann.controller;

import static com.mcann.constant.RestApis.*;

import com.mcann.dto.request.AddLineRequestDto;
import com.mcann.dto.response.BaseResponse;
import com.mcann.entity.Line;
import com.mcann.service.LineService;
import com.mcann.service.StationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(LINE)
@RequiredArgsConstructor
public class LineController {
	private final LineService lineService;
	
	@PostMapping(ADD)
	public ResponseEntity<BaseResponse<Boolean>> addLine(AddLineRequestDto dto){
		lineService.addLine(dto);
		return ResponseEntity.ok(BaseResponse.<Boolean>builder()
		                                     .message("Hat başarıyla eklendi")
		                                     .code(200)
		                                     .success(true).data(true)
		                                     .build()
		);
	}
	
	@GetMapping(FINDALL)
	public ResponseEntity<BaseResponse<List<Line>>> getAllLine(){
		return ResponseEntity.ok(
				BaseResponse.<List<Line>>builder()
				            .data(lineService.getAllLines())
				            .success(true)
				            .code(200)
				            .message("Tüm hatlar başarıyla listelendi")
				            .build()
		);
	}
	
}