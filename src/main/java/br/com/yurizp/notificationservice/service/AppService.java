package br.com.yurizp.notificationservice.service;

import br.com.yurizp.notificationservice.controller.request.app.CreateAppRequest;
import br.com.yurizp.notificationservice.controller.response.app.AppResponse;
import br.com.yurizp.notificationservice.db.AppRepository;
import br.com.yurizp.notificationservice.db.UserRepository;
import br.com.yurizp.notificationservice.db.entity.AppEntity;
import br.com.yurizp.notificationservice.db.entity.UserEntity;
import br.com.yurizp.notificationservice.exceptions.AppNotFoundException;
import br.com.yurizp.notificationservice.exceptions.UserNotFoundException;
import br.com.yurizp.notificationservice.mapper.AppEntityMapper;
import br.com.yurizp.notificationservice.mapper.AppResponseMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AppService {

    private final AppRepository appRepository;
    private final UserRepository userRepository;

    public AppResponse createApp(CreateAppRequest createApp, final Long userId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException());
        AppEntity appEntity = appRepository.save(AppEntityMapper.create(createApp, user));
        return AppResponseMapper.create(appEntity);
    }

    public AppResponse findAppById(final Long appId, final Long userId) {
        AppEntity appEntity =
                appRepository.findByIdAndUserId(appId, userId).orElseThrow(() -> new AppNotFoundException());

        return AppResponseMapper.create(appEntity);
    }
}
