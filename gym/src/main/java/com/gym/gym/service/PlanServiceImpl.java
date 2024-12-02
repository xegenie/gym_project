package com.gym.gym.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.gym.domain.Plan;
import com.gym.gym.mapper.PlanMapper;

@Service
public class PlanServiceImpl implements PlanService {
    
    @Autowired
    PlanMapper planMapper;
    
    @Override
    public List<Plan> listAll() throws Exception {
        List<Plan> planList = planMapper.listAll(); 
        return planList;
    }
    
    @Override
    public List<Plan> selectByUser(int userNo) throws Exception {
        List<Plan> planList = planMapper.selectByUser(userNo);
        return planList;
    }

    @Override
    public List<Plan> selectByUserMonth(int userNo, Date startTime) throws Exception {

        LocalDateTime localDateTime = startTime.toInstant()
                                            .atZone(ZoneId.systemDefault())
                                            .toLocalDateTime();

        // Initialize startTime to the first day of the month at 00:00:00
        LocalDateTime startOfMonth = localDateTime.withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0);

        // Initialize endTime to the last day of the month at 23:59:59
        LocalDateTime endOfMonth = localDateTime.withDayOfMonth(localDateTime.toLocalDate().lengthOfMonth())
                                                .withHour(23).withMinute(59).withSecond(59);

        // Convert LocalDateTime back to Date
        startTime = Date.from(startOfMonth.atZone(ZoneId.systemDefault()).toInstant());
        Date endTime = Date.from(endOfMonth.atZone(ZoneId.systemDefault()).
        toInstant());

        List<Plan> planList = planMapper.selectByUserDate(userNo, startTime, endTime);

        return planList;
    }
    
    @Override
    public List<Plan> selectByUserDay(int userNo, Date startTime) throws Exception {
        LocalDateTime localDateTime = startTime.toInstant()
                                            .atZone(ZoneId.systemDefault())
                                            .toLocalDateTime();

        // Initialize startTime to the first day of the month at 00:00:00
        LocalDateTime startOfMonth = localDateTime.withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0);

        // Initialize endTime to the last day of the month at 23:59:59
        LocalDateTime endOfMonth = localDateTime.withDayOfMonth(localDateTime.toLocalDate().lengthOfMonth())
                                                .withHour(23).withMinute(59).withSecond(59);

        // Convert LocalDateTime back to Date
        startTime = Date.from(startOfMonth.atZone(ZoneId.systemDefault()).toInstant());
        Date endTime = Date.from(endOfMonth.atZone(ZoneId.systemDefault()).
        toInstant());

        List<Plan> planList = planMapper.selectByUserDate(userNo, startTime, endTime);

        return planList;
    }

    @Override
    public Plan selectByNo(int no) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectByNo'");
    }

    @Override
    public int insert(Plan plan) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
    }

    @Override
    public int update(Plan plan) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public int delete(int no) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
}
