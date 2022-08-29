package com.ll.exam.app3.repository;

import com.ll.exam.app3.entity.SiteUser;

import java.util.List;

public interface UserRepositoryCustom {
    SiteUser getQslUser(Long id);

    Long getQslCount();

    SiteUser getQslUserOrderByIdAscLimit1();

    List<SiteUser> getQslUsersOrderByIdAsc();
}
