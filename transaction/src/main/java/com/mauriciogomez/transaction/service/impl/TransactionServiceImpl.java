package com.mauriciogomez.transaction.service.impl;

import com.mauriciogomez.transaction.client.OpenFeignClient;
import com.mauriciogomez.transaction.dto.TransactionRequest;
import com.mauriciogomez.transaction.dto.TransactionResponse;
import com.mauriciogomez.transaction.service.TransactionService;

import com.mauriciogomez.transaction.util.AesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private OpenFeignClient openFeignClient;
    @Autowired
    private AesUtil aesUtil;

    @Override
    public TransactionResponse create(TransactionRequest request) {
        String decryptedSecret = aesUtil.decrypt(request.getSecreto());

        if (decryptedSecret.isEmpty()) {
            throw new RuntimeException("Invalid secret");
        }
        request.setSecreto(decryptedSecret);

        return openFeignClient.createTransaction(request);
    }

}
