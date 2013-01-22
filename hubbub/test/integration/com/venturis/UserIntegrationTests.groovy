package com.venturis

import static org.junit.Assert.*
import org.junit.*

class UserIntegrationTests {
	
	@Before
	void setUp() {}

	@After
	void tearDown() {}	

    @Test
    void testFirstSaveEver() {
    
		def user = new User(userId: 'joe', password: 'secret', homepage: 'http://www.grailsinaction.com')	
//		assert user.validate(), user.errors
		
		assertNotNull user.save() 
		assertNotNull user.id
		
		def foundUser = User.get(user.id)
		assertEquals 'joe', foundUser.userId
    }
    
	@Test
	void testSaveAndUpdate() {
		
		def user2 = new User(userId: 'joe2', password: 'secret', homepage: 'http://www.grailsinaction.com')
		assertNotNull user2.save()			
		
		def foundUser = User.get(user2.id)		
		foundUser.password = 'sesame'
		foundUser.save()
		
		def editedUser = User.get(user2.id)
		assertEquals 'sesame', editedUser.password
	}   
	
	@Test
	 void testSaveThenDelete() {
		def user = new User(userId: 'joe3', password: 'secret', homepage: 'http://www.grailsinaction.com')
		assertNotNull user.save()
		
		def foundUser = User.get(user.id)		
		foundUser.delete()	
		assertFalse User.exists(foundUser.id)
	}	 
	 
	 @Test
	 void testFollowing() {
		 
		 def glen = new User(userId: 'glen', password:'password').save()
		 def peter = new User(userId: 'peter', password:'password').save()
		 def sven = new User(userId: 'sven', password:'password').save()
		 
		 glen.addToFollowing(peter)
		 glen.addToFollowing(sven)
		 assertEquals 2, glen.following.size()
		 
		 sven.addToFollowing(peter)
		 assertEquals 1, sven.following.size()
	 }
}