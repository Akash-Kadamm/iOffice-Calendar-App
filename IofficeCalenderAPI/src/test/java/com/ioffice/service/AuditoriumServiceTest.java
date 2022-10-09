package com.ioffice.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Map;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ioffice.model.Auditoriums;
import com.ioffice.repository.AuditoriumRepository;

public class AuditoriumServiceTest {

	@Mock
	AuditoriumRepository auditoriumRepo;
	
	@InjectMocks
	AuditoriumService auditoriumService;
	
	Optional<Auditoriums> auditorium;
	
	Auditoriums auditoriums;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		setData();
		setMocks();
	}
	
	private void setMocks() {
		when(auditoriumRepo.findById(anyInt())).thenReturn(auditorium);
	}
	
	private void setData() {
		auditorium=Optional.of(new Auditoriums("Audi1", "CT1", 120, "small", "AC,Network"));
		auditoriums=new Auditoriums("Audi1", "CT1", 120, "small", "AC,Network");
	}

	@Test
	public void testGetAuditoriumById_ConditionReturnAuditorium() {
		Map<String, Object> actual=auditoriumService.getAuditoriumById(1);
		assertEquals(auditoriums, actual.get("Auditorium"));
		
	}

	@Test
	public void testupdateAuditorium() {
		auditoriumService.updateAuditorium(1, auditoriums);
		verify(auditoriumRepo, times(1)).findById(1);
	}
	
	

}
