package ru.grishankov.marketin

import java.util.*
import kotlin.properties.Delegates

var HOST by Delegates.notNull<String>()
var PORT by Delegates.notNull<Int>()

const val CFG_FOLDER_PATH = "config"
const val CFG_FILE_PATH = "settings.cfg"

val PATH_SERVER_FILES by lazy {
    "http://$HOST:$PORT/files"
}

val configProps = Properties()

var BD_URL by Delegates.notNull<String>()
var BD_DRIVER by Delegates.notNull<String>()
var BD_USER by Delegates.notNull<String>()
var BD_PASSWORD by Delegates.notNull<String>()