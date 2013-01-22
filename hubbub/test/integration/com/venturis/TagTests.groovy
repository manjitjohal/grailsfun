package com.venturis

import static org.junit.Assert.*
import org.junit.*

class TagTests {

    @Before
    void setUp() {
    }

    @After
    void tearDown() {
    }

		@Test
	    void testFirstPost() {
	
			def user = new User(userId:'joesmith', password: 'secret').save()
	
			def post1 = new Post(content:"this is my first post wohoo")
			user.addToPosts(post1)
	
			def post2 = new Post(content:"this is my second post wohoo")
			user.addToPosts(post2)
	
			assertEquals 2, User.get(user.id).posts.size()
	
	    }
		
		@Test
		void testAccessingPosts() {
			def user = new User(userId: 'joe', password: 'secret').save()
			
			user.addToPosts(new Post(content: "First"))
			user.addToPosts(new Post(content: "Second"))
			user.addToPosts(new Post(content: "Third"))
			
			def foundUser = User.get(user.id)
			def postNames =
			foundUser.posts.collect { it.content }
			assertEquals(['First', 'Second', 'Third'],
			postNames.sort())
			}
	
	@Test
	void testPostWithTags() {
		
			def userTwo = new User(userId:'joesmith13', password: 'secret')			
			userTwo.save()
					
			def tagGroovy = new Tag(name: 'groovy')
			def tagGrails = new Tag(name: 'grails')
			
			userTwo.addToTags(tagGroovy)
			userTwo.addToTags(tagGrails)
			
			def tagNames = userTwo.tags*.name
			assertEquals([ 'grails', 'groovy'] , tagNames.sort())
			
			def groovyPost = new Post(content: "A groovy post")
			userTwo.addToPosts(groovyPost)
			groovyPost.addToTags(tagGroovy)			
			assertEquals 1, groovyPost.tags.size()
			
			def bothPost = new Post(content: "A groovy and grails post")
			userTwo.addToPosts(bothPost)
			bothPost.addToTags(tagGroovy)
			bothPost.addToTags(tagGrails)
			assertEquals 2, bothPost.tags.size()
		}
}