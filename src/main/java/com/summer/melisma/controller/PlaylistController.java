package com.summer.melisma.controller;

import java.util.List;
import java.util.UUID;

import com.summer.melisma.model.dto.PlaylistDto;
import com.summer.melisma.model.vo.PlaylistVo;
import com.summer.melisma.service.PlaylistService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/playlists")
@RequiredArgsConstructor
public class PlaylistController {
    private final PlaylistService playlistService;
    
    /**
     * <b>playlist 생성</b>
     * 
     * @param dto : PlaylistDto
     * @return ResponseEntity
     */
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody PlaylistDto dto) {
        playlistService.create(dto);

        // TODO :: 리턴값 수정해야 함
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    /**
     * <b>id에 대응하는 playlist 조회</b>
     * 
     * @param id : UUID
     * @return ResponseEntity
     */
    @GetMapping("/search/{id}")
    public ResponseEntity<?> search(@PathVariable UUID id) {
        PlaylistVo vo = new PlaylistVo();
        
        try{
            vo = playlistService.search(id);
        } catch (NullPointerException e) {
            vo = null;
        }

        return new ResponseEntity<>(vo, HttpStatus.OK);
    }

    /**
     * <b>playlist 전체 조회</b>
     * 
     * @return ResponseEntity
     */
    @GetMapping("/searchList")
    public ResponseEntity<?> searchList() {
        List<PlaylistVo> vos = playlistService.searchList();

        return new ResponseEntity<>(vos, HttpStatus.OK);
    }

    /**
     * <b>로그인된 user의 playlist 조회</b>
     * 
     * @return ResponseEntity
     */
    @GetMapping("/searchList/users")
    public ResponseEntity<?> searchListByUser() {
        List<PlaylistVo> vos = playlistService.searchListByUser();

        return new ResponseEntity<>(vos, HttpStatus.OK);
    }

    /**
     * <b>id에 대응하는 pklaylist 삭제</b>
     * 
     * @param id : UUID
     * @return ResponseEntity
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        String message = "success";

        try {
            playlistService.delete(id);
        } catch (NullPointerException e) {
            message = "not found";
        }

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    /**
     * <b>playlist 수정</b>
     * 
     * @param dto : PlaylistDto
     * @return ResponseEntity
     */
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody PlaylistDto dto) {
        String message = "success";

        try {
            playlistService.update(dto);
        } catch (NullPointerException e) {
            message = "not found";
        }

        return new ResponseEntity<>(message, HttpStatus.OK);
    }
 
    /**
     * <b>playlist 단일 수정</b>
     * 
     * @param dto : PlaylistDto
     * @return ResponseEntity
     */
    @PatchMapping("/change")
    public ResponseEntity<?> change(@RequestBody PlaylistDto dto) {
        String message = "success";

        try {
            playlistService.change(dto);
        } catch (NullPointerException e) {
            message = "not found";
        }

        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
