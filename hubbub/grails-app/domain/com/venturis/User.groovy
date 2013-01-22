package com.venturis

import sun.awt.AppContext.PostShutdownEventRunnable;

class User {

	String userId
	String password
	String homepage
	Date dateCreated

	Profile profile

	static constraints = {
		userId(size:3..20, unique: true)
		password(size: 6..8, validator: {passwd, user -> passwd != user.userId})
		profile(nullable:true)
		dateCreated()
		homepage(url: true, nullable: true)
	}
	
	static mapping = {profile lazy:false }
	static hasMany = [posts:Post, tags:Tag, following:User]
	
	String toString() {
		"${userId}"
		}
}