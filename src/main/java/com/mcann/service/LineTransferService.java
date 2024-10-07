package com.mcann.service;

import com.mcann.repository.LineTransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LineTransferService {
	
	private final LineTransferRepository lineTransferRepository;
}