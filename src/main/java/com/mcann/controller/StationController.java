package com.mcann.controller;

import static com.mcann.constant.RestApis.*;

import com.mcann.dto.request.AddStationRequestDto;
import com.mcann.dto.response.BaseResponse;
import com.mcann.entity.Station;
import com.mcann.service.StationService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(STATION)
@RequiredArgsConstructor
public class StationController {
	private final StationService stationService;
	
	@PostMapping(ADDSTATION)
	public ResponseEntity<BaseResponse<Boolean>> addStation(@RequestBody AddStationRequestDto dto){
		stationService.addStation(dto);
		return ResponseEntity.ok(
				BaseResponse.<Boolean>builder()
				            .success(true)
				            .code(200)
				            .message("İstasyon başarıyla eklendi")
				            .data(true)
				            .build()
		);
	}
	
	@GetMapping(FINDALL)
	public ResponseEntity<BaseResponse<List<Station>>> getAllStation(){
		return ResponseEntity.ok(BaseResponse.<List<Station>>builder()
		                                     .code(200)
		                                     .message("Tüm istasyonlar listelendi.")
		                                     .success(true)
		                                     .data(stationService.findAll())
		                                     .build()
		);
	}
}