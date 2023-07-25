/*
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 *
 * @Time    : 7/25/2023 21:27
 * @Author  : Tony Skywalker
 * @File    : IShopService.java
 */

package tms.exec.service.impl;

import tms.exec.service.IService;

import java.util.List;

public interface IShopService extends IService {
    void register(List<String> args);

    void list(List<String> args);

    void cancel(List<String> args);
}
