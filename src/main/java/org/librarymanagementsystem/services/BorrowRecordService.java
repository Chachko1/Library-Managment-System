package org.librarymanagementsystem.services;

import org.librarymanagementsystem.DTOs.BorrowRecordDTO;
import org.librarymanagementsystem.mappers.BorrowRecordMapper;
import org.librarymanagementsystem.models.BorrowRecord;
import org.librarymanagementsystem.repositories.BorrowRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BorrowRecordService {

    @Autowired
    private BorrowRecordMapper borrowRecordMapper;

    @Autowired
    private BorrowRecordRepository borrowRecordRepository;

    public List<BorrowRecordDTO>  getAllBorrowRecords (){
        return borrowRecordRepository.findAll().stream().map(borrowRecordMapper::toDTO).collect(Collectors.toList());

    }

    public BorrowRecordDTO saveBorrowRecord (BorrowRecordDTO borrowRecordDTO){
        BorrowRecord borrowRecord= borrowRecordMapper.toEntity(borrowRecordDTO);
        return borrowRecordMapper.toDTO(borrowRecordRepository.save(borrowRecord));

    }

    public void deleteBorrowRecord(Long id ){
        borrowRecordRepository.deleteById(id);
    }
}
