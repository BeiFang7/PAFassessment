package sg.edu.nus.iss.app.workshop24.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.app.workshop24.model.Transaction;

@Service
public class LogAuditService {
  @Autowired
  private RedisTemplate<String, String> redisTemplate;

  public int save(Transaction transaction){
    
    redisTemplate.opsForValue().set(transaction.getTransactionId(),transaction.toJSON().toString());
    return 1;
  }

}
