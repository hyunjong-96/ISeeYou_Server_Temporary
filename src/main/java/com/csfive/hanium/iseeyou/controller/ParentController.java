package com.csfive.hanium.iseeyou.controller;

import com.csfive.hanium.iseeyou.domain.student.Student;
import com.csfive.hanium.iseeyou.dto.parent.LoginRequestDto;
import com.csfive.hanium.iseeyou.dto.parent.ParentUpdateRequestDto;
import com.csfive.hanium.iseeyou.dto.student.StudentAcceptanceReqDto;
import com.csfive.hanium.iseeyou.dto.student.StudentDetailResDto;
import com.csfive.hanium.iseeyou.service.ParentService;
import com.csfive.hanium.iseeyou.dto.parent.ParentSavetRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.csfive.hanium.iseeyou.utils.ResponseMessage.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/parents")
@RestController
public class ParentController {

    private final ParentService parentService;

    @PostMapping("/")
    public ResponseEntity<String> signup(@RequestBody ParentSavetRequestDto saveRequestDto){
        parentService.signup(saveRequestDto);
        return ResponseEntity.ok(CREATE_USER);
    }

    @PostMapping("/{parentId}/acceptance")
    public ResponseEntity<String> acceptanceRequest(@PathVariable("parentId") Long parentId,@RequestBody StudentAcceptanceReqDto studentAcceptanceReqDto){
        parentService.accpetanceStudent(parentId, studentAcceptanceReqDto);
        return ResponseEntity.ok(SAVE_SUCCESS);
    }

    @GetMapping("/{parentId}/students")
    public ResponseEntity<List<StudentDetailResDto>> findStudent(@PathVariable("parentId")Long id){
        List<StudentDetailResDto> studentList = parentService.findStudents(id);

        return ResponseEntity.ok(studentList);
    }

    @PutMapping("/{parentId}")
    public ResponseEntity<Long> update(@PathVariable("parentId") Long id, @RequestBody ParentUpdateRequestDto updateRequestDto){
        Long studentid = parentService.update(id, updateRequestDto);
        return ResponseEntity.ok(studentid);
    }

    @DeleteMapping("/{parentId}")
    public ResponseEntity<String> delete(@PathVariable("parentId") Long id){
        parentService.delete(id);
        return ResponseEntity.ok(DELETE_USER);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDto loginRequestDto){
        Long id = parentService.login(loginRequestDto);

        return ResponseEntity.ok(LOGIN_SUCCESS);
    }


}
