package com.example.auditing.repositories.dummytables;

import com.example.auditing.models.dummytables.ApplicationModel;
import com.example.auditing.repositories.base.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ApplicationRepository extends BaseRepository<ApplicationModel,Long> {
    ApplicationModel findAppByName(String name);
    List<String> getAllApps();
}
