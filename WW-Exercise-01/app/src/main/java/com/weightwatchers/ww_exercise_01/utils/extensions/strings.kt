package com.weightwatchers.ww_exercise_01.utils.extensions

import com.weightwatchers.ww_exercise_01.utils.network.NetworkUtil.BASE_URL

fun String.addBaseUrl():String = "$BASE_URL$this"