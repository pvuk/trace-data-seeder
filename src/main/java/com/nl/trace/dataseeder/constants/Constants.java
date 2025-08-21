package com.nl.trace.dataseeder.constants;

public class Constants {
	
	public static interface DATE {
		public static final String E_dd_MMM_yyyy_HH_mm_ss_S = "E dd-MMM-yyyy HH:mm:ss.S";
		public static final String E_dd_MMM_yyyy_HH_mm_ss_S_z = "E dd-MMM-yyyy HH:mm:ss.S z";
	}
	
	public static interface KAFKA {
		
		public static interface CONSUMER {
			public static interface GROUP_ID {
				public static final String KAFKA_GROUP_TRACE_BANK_ID = "kafka-group-trace-bank-id";
			}
		}
		
		public static interface TOPIC_NAME {
			public static final String TOPIC_TRACE_BANK = "topic-trace-bank";
		}
	}
	
	public static interface Status {
		public static final String SUCCESS = "SUCCESS";
		public static final String FAILED = "FAILED";
		public static final String IN_PROGRESS = "IN_PROGRESS";
		public static final String PENDING = "PENDING";
	}
	
	public interface PORT {
		public static final String H2_TCP_PORT = "9010";
	}
}
