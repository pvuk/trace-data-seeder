package com.nl.trace.dataseeder.service.impl;

import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;

@Transactional(rollbackOn = Exception.class)
@Service
public class CardsServiceImpl {
	
	//Constructor dependency injection
//	private final CardNetworkRepository cardNetworkRepository;
//	
//	public CardsServiceImpl(CardNetworkRepository cardNetworkRepository) {
//		this.cardNetworkRepository = cardNetworkRepository;
//	}
	
	/**
	 * The @PostConstruct annotation in Java is used to mark a method that should be executed <b>after dependency injection is done</b>
	 *  to perform any initialization.
	 *  
	 *  🔍 What Happens Here?
	 *  When Spring creates an instance of CardsServiceImpl, it first calls the constructor.
		After the constructor and dependency injection are complete, it calls the init() method annotated with @PostConstruct.
	 * 
	 * 🧠 Notes
		The method annotated with @PostConstruct must be void, take no arguments, and should not throw checked exceptions.
		It’s a good place to put initialization logic that depends on injected dependencies.

	Code Ref:
	<b>@PostConstruct is not related to JPA auditing.</b> 
	If you're expecting AuditorAware to be called during @PostConstruct, 
	it won’t be — because auditing is triggered during entity persistence, not bean initialization.

	 */
	@PostConstruct
	public void init() {
//		List<CardNetwork> list = new ArrayList<>();
//		Date updatedDate = new Date();
//		
//		CardNetwork visaCardNetwork = CardNetwork.builder().cardNetworkId(1).cardNetworkName("Visa")
//		.description("One of the largest global payment networks, widely accepted worldwide.")
//		.build();
//		visaCardNetwork.setCreatedOn(updatedDate);
//		visaCardNetwork.setUpdatedOn(updatedDate);
//		list.add(visaCardNetwork);
//		
//		list.add(CardNetwork.builder().cardNetworkId(2).cardNetworkName("MasterCard")
//				.description("Another major global network, known for broad international acceptance.").build());
//		
//		list.add(CardNetwork.builder().cardNetworkId(3).cardNetworkName("RuPay")
//				.description("India-based card network developed by NPCI, widely accepted in India.").build());
//		
//		list.add(CardNetwork.builder().cardNetworkId(4).cardNetworkName("American Express (Amex)")
//				.description("Offers both card issuing and network services; known for premium cards.").build());
//		
//		list.add(CardNetwork.builder().cardNetworkId(5).cardNetworkName("Discover")
//				.description("U.S.-based network, also operates Diners Club International.").build());
//		
//		list.add(CardNetwork.builder().cardNetworkId(6).cardNetworkName("Diners Club")
//				.description("A part of Discover; known for travel and premium services.").build());
//			
//		list.add(CardNetwork.builder().cardNetworkId(7).cardNetworkName("UnionPay")
//				.description("China-based network, rapidly expanding globally.").build());
//		
//		list.add(CardNetwork.builder().cardNetworkId(8).cardNetworkName("JCB (Japan Credit Bureau)")
//				.description("Japan-based network with international reach.").build());
//		
//		list.add(CardNetwork.builder().cardNetworkId(9).cardNetworkName("BC Card")
//				.description("South Korea-based network.").build());
//		
//		list.add(CardNetwork.builder().cardNetworkId(10).cardNetworkName("Troy")
//				.description("Turkey’s domestic card scheme.").build());
//		
//		list.add(CardNetwork.builder().cardNetworkId(11).cardNetworkName("Verve")
//				.description("Nigeria-based card network, part of Interswitch.").build());
////		CardNetwork cardNetwork = new CardNetwork();
////		cardNetwork.setCreatedBy(null);
//		cardNetworkRepository.saveAll(list);
	}
	
}
