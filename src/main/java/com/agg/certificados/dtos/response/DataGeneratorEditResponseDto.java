package com.agg.certificados.dtos.response;

import com.agg.certificados.dtos.request.ManagerDataGeneratorRequestDto;
import com.agg.certificados.dtos.request.QuantitiesRcdRequestDto;
import com.agg.certificados.dtos.request.QuantitiesTotal;

import java.util.List;

public class DataGeneratorEditResponseDto {
    public Long id_data_generator;
    public BotaderoResponseDto botadero;
    public DataManagerResponseDto data_manager;
    public ManagerDataGeneratorRequestDto manager;
    public String unic_number;
    public String name;
    public TypeDocumentResponseDto type_document;
    public String number_id;
    public String legal_representative;
    public String address;
    public Long phone_number;
    public String email;
    public String address_rcd;
    public String reception_date_rcd;
    public Long total_rcd;
    public QuantitiesRcdRequestDto quantitiesRcd;
    public DataDriverResponseDto data_driver;
    public CertificationMiniResponseDto certification;
}
