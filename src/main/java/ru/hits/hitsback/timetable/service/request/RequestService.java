package ru.hits.hitsback.timetable.service.request;

import ru.hits.hitsback.timetable.model.dto.request.AcceptDto;
import ru.hits.hitsback.timetable.model.dto.request.GroupChangingRequestDto;
import ru.hits.hitsback.timetable.model.dto.request.GroupChangingRequestIdDto;
import ru.hits.hitsback.timetable.model.dto.request.RegistrationRequestDto;

import java.util.List;

public interface RequestService {

    public List<RegistrationRequestDto> fetchRegistrationRequests();

    List<GroupChangingRequestDto> fetchGroupChangingRequests();

    AcceptDto resolveRegistrationRequest(String id, Boolean accept);

    AcceptDto resolveGroupChangingRequest(GroupChangingRequestIdDto groupChangingRequestIdDto, Boolean accept);

}
