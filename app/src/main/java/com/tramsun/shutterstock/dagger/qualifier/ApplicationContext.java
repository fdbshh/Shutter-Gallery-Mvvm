/*
 * Copyright (c) 2011,2012,2013,2014,2015 Mutual Mobile. All rights reserved.
 */

package com.tramsun.shutterstock.dagger.qualifier;

import java.lang.annotation.Retention;
import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Qualifier @Retention(RUNTIME) public @interface ApplicationContext {
}
