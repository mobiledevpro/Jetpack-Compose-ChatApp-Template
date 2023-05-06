package com.mobiledevpro.domain.model

/**
 * Chat
 *
 * Created on May 06, 2023.
 *
 */
data class Chat(
    val user: UserProfile,
    val peopleList: List<PeopleProfile>
)

val fakeUser = UserProfile(
    name = "Your Name",
    status = true,
    photoUrl = ""
)

val fakeChatList = arrayListOf(
    Chat(
        fakeUser,
        fakePeopleProfileList.take(4)
    )
)


