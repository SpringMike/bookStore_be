package com.example.demo.controller;

import com.example.demo.config.PaymentConfig;
import com.example.demo.dto.PaymentDTO;
import com.example.demo.dto.PaymentResultDTO;
import com.example.demo.model.Order;
import com.example.demo.model.TransactionHistory;
import com.example.demo.service.ITransactionService;
import com.example.demo.service.impl.OrderDetailService;
import com.example.demo.service.impl.OrderService;
import lombok.RequiredArgsConstructor;
import org.hibernate.Transaction;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payments")
@CrossOrigin("*")
public class PaymentController {

    private final ITransactionService transactionService;
    private final OrderService orderService;
    private final OrderDetailService orderDetailService;

    @PostMapping("/create-payment")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<?> createPayment(@RequestBody PaymentDTO paymentDTO) throws UnsupportedEncodingException {
        int amount = paymentDTO.getAmount() * 100;
        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", PaymentConfig.VERSIONVNPAY);
        vnp_Params.put("vnp_Command", PaymentConfig.COMMAND);
        vnp_Params.put("vnp_TmnCode", PaymentConfig.TMNCODE);
        vnp_Params.put("vnp_Amount", String.valueOf(amount));
        vnp_Params.put("vnp_CurrCode", PaymentConfig.CURRCODE);
        vnp_Params.put("vnp_BankCode", paymentDTO.getBankCode());
        vnp_Params.put("vnp_TxnRef", String.valueOf(PaymentConfig.getRandomNumber(8)));
        vnp_Params.put("vnp_OrderInfo", paymentDTO.getDescription());
        vnp_Params.put("vnp_OrderType", PaymentConfig.ORDERTYPE);
        vnp_Params.put("vnp_Locale", PaymentConfig.LOCALDEFAULT);
        vnp_Params.put("vnp_ReturnUrl", PaymentConfig.RETURNURL);
        vnp_Params.put("vnp_IpAddr", PaymentConfig.IDDEFAULT);

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(date);
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

        //Build data to hash and querystring
        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                //Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));

                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = PaymentConfig.hmacSHA512(PaymentConfig.CHECKSUM, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = PaymentConfig.VNPAYURL + "?" + queryUrl;


        PaymentResultDTO result = new PaymentResultDTO();
        result.setStatus("00");
        result.setMessage("success");
        result.setUrl(paymentUrl);
        return ResponseEntity.ok(result);
    }
    @GetMapping("/payment-info")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<?> paymentInfo(
            @RequestParam(value = "vnp_Amount",required = false) String amount,
            @RequestParam(value = "vnp_BankCode",required = false) String bankCode,
            @RequestParam(value = "vnp_BankTranNo",required = false) String bankTranNo,
            @RequestParam(value = "vnp_CardType",required = false) String cardType,
            @RequestParam(value = "vnp_PayDate",required = false) String payDate,
            @RequestParam(value = "vnp_OrderInfo",required = false) String orderInfo,
            @RequestParam(value = "vnp_ResponseCode",required = false) String responseCode,
            @RequestParam(value = "vnp_TxnRef",required = false) String txnRef,
            @RequestParam(value = "vnp_SecureHashType",required = false) String secureHashType,
            @RequestParam(value = "vnp_SecureHash",required = false) String secureHash,
            @RequestParam(value = "accountId",required = false) String accountId

    )  {
        Order order = orderService.findNewstOrderByAccount(Long.valueOf(accountId));
        TransactionHistory transaction1 = transactionService.findByOrderId(order.getId());
        if (transaction1 == null){
            TransactionHistory transaction = new TransactionHistory();
            transaction.setAmount(Float.valueOf(amount));
            transaction.setBankCode(bankCode);
            transaction.setBankTranNo(bankTranNo);
            transaction.setCardType(cardType);
            transaction.setPayDate(payDate);
            transaction.setOrderInfo(orderInfo);
            transaction.setResponseCode(responseCode);
            transaction.setOrderId(order.getId());
            transaction.setSecureHashType(secureHashType);
            transaction.setSecureHash(secureHash);
            orderDetailService.findByOrderIdAndUpdateQuantityBook(order.getId());
            transactionService.save(transaction);
            return ResponseEntity.ok(transaction);
        }
        return ResponseEntity.ok(transaction1);
    }
}
