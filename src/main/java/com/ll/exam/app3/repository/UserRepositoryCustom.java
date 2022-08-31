package com.ll.exam.app3.repository;

import com.ll.exam.app3.entity.SiteUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserRepositoryCustom {
    SiteUser getQslUser(Long id);

    Long getQslCount();

    SiteUser getQslUserOrderByIdAscLimit1();

    List<SiteUser> getQslUsersOrderByIdAsc();

    List<SiteUser> searchQsl(String s);

    Page<SiteUser> searchQsl(String user, Pageable pageable);

    List<SiteUser> listQslByInterests(String keyword);
}
