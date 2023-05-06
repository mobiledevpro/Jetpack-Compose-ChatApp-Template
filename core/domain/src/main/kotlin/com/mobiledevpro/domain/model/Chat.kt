package com.mobiledevpro.domain.model

/**
 * Chat
 *
 * Created on May 06, 2023.
 *
 */
data class Chat(
    val user: UserProfile,
    val peopleList: List<PeopleProfile>,
    val unreadMsgCount : Int = 0
)

val fakeUser = UserProfile(
    name = "Your Name",
    status = true,
    photoUrl = ""
)

val fakeChatList = arrayListOf(
    Chat(
        user = fakeUser,
        peopleList = fakePeopleProfileList.take(5).sortedByDescending { !it.status },
        unreadMsgCount = 100
    ),
    Chat(
        user = fakeUser,
        peopleList = fakePeopleProfileList.takeLast(3).sortedByDescending { !it.status },
        unreadMsgCount = 0
    ),

    Chat(
        user = fakeUser,
        peopleList = listOf(fakePeopleProfileList[6]),
        unreadMsgCount = 3
    )
)

fun Chat.name() : String =
    this.peopleList.toChatName()
