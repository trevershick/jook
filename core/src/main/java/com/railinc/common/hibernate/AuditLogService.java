package com.railinc.common.hibernate;

import java.util.List;

public interface AuditLogService {
	
	List<String> entityIdsInvolvedInTx(String txRef, String entityType);
	List<String> entityTypesInvolvedInTx(String txRef);
	
	List<AuditLogRecord> records(String entityType, String entityId,String txRef);
	List<String> txReferences(String entityType, String entityId);
	
}
