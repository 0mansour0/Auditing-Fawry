package com.example.auditing.services.dummytables;

import com.example.auditing.models.dummytables.ApplicationModel;

import java.util.List;

public interface ApplicationService {
    ApplicationModel findAppByName(String name);

    List<String> getApplications();
}
