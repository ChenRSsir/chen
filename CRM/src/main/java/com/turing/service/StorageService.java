package com.turing.service;

import java.util.List;


import com.turing.entity.Storage;

/**
 * ���ҵ����
 * @author Administrator
 *
 */
public interface StorageService {

   public List<Storage> selectProStorage(String prodName,String stkWarehouse);
}
