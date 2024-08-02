package com.PartnersFunds.service;

import java.util.List;

public interface ExpressionService {
	
	ResponseExpressionDTO saveExpression(ExpressionDTO expressionDTO);

//   void saveExpressions(List<ExpressionDTO> expressionDTOs);

   void saveAll(List<ExpressionAttrPropDTO> dtoList);
}
