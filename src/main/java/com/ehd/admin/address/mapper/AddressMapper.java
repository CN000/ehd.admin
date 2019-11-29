package com.ehd.admin.address.mapper;

import com.ehd.admin.address.domain.Address;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AddressMapper {
    Address getPrivateKeyByAddress(@Param("address") String address);
}
