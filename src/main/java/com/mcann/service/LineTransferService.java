package com.mcann.service;

import com.mcann.repository.LineTransferRepository;
import org.springframework.stereotype.Service;

@Service
public class LineTransferService {
	
	private final LineTransferRepository lineTransferRepository;
	
	public LineTransferService(LineTransferRepository lineTransferRepository) {
		this.lineTransferRepository = lineTransferRepository;
	}
}