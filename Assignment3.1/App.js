import React, { Component } from 'react';
import { StyleSheet, Image, Text, View, Button, ImageBackground, FlatList, Linking, TouchableOpacity, AsyncStorage} from 'react-native';
import {List, ListItem} from 'react-native-elements'
import { StackNavigator } from 'react-navigation';
import api from './app/api';
import axios from 'axios'

/*
 homeScreen display
*/
class homeScreen extends Component {
	constructor(props){
    super(props);
    this.state = {isLoading: true, username: '',name: '', createDate: '', bio: '', avatarUrl: '', url: 'https://api.github.com/users/alsan1997', reposUrl: '', followersUrl: '' }
  }

  componentWillMount(){

  	axios.get(this.state.url, {params: {access_token: 'da56564fc9f431f0d09653aba249f4e1e1056221'}}).then(response =>{
  		//console.warn(response)
  		// {params: {access_token: 'da56564fc9f431f0d09653aba249f4e1e1056221'}}
  		this.setState({  			
  			isLoading: false,
  			name: response.data.name,
        username: response.data.login,
        createDate: response.data.created_at,
        bio: response.data.bio,	
        reposUrl: response.data.repos_url,
        followersUrl: response.data.followers_url,
        avatarUrl: response.data.avatar_url,
      });
  	})
     /*api.getData().then((res) =>{
      this.setState({
        isLoading: false,
        name: res.name,
        username: res.login,
        createDate: res.created_at,
        bio: res.bio,	
        avatarUrl: res.avatar_url
      }) 
     })*/
  }

	static navigationOptions = {
			title: 'Home',
	};
	render(){
		var { navigate } = this.props.navigation;
		
		if(this.state.isLoading){
      return(
        <View style={{flex: 1, padding: 20}}>
          <Text> Loading github profile...</Text>
        </View>
      )
    }

		 
		return(
			
			<ImageBackground
					source = {require('./app/img/background2.jpg')}
					style = {styles.bgContainer}>
					<View style={styles.container}>

						<Image source={{uri:this.state.avatarUrl}} style={{width: 193, height: 170}}/> 
						<View style = {{justifyContent: 'center'}}>
							<Text> Name: {this.state.name} </Text>
							<Text> Username: {this.state.username} </Text>
							<Text> Email: aldosanjoto@gmail.com </Text>
							<Text> Bio: {this.state.bio} </Text>
							<Text> Created at: {this.state.createDate}</Text>

						</View>
						<Button
								
								title="Repositories"
								color="#33C6FF"
								onPress={()=>this.props.navigation.navigate('Repo', {url: this.state.reposUrl})}
						/>			
						<Button
								
								title="My Followers"
								color="#33C6FF"
								onPress={()=>this.props.navigation.navigate('Follower', {followers: this.state.followersUrl})}
						/>			
						<Button
								
								title="My Followings"
								color="#33C6FF"
								onPress={()=>this.props.navigation.navigate('Following', {username: this.state.username})}
						/>			

						<Button

								title = "Save myData"
								color = "#33C6FF"
								onPress={() => this.props.navigation.navigate('Data', {followers: this.state.followersUrl,url: this.state.reposUrl, username: this.state.username, bio: this.state.bio, date: this.state.createDate, name: this.state.name})}
						/>
					</View>
			</ImageBackground>
		);
	}
}

/*
 childHomeScreen display
*/
class childHomeScreen extends Component {
	constructor(props){
    super(props);
    this.state = {isLoading: true, username: '',name: '', createDate: '', bio: '', avatarUrl: '', url: this.props.navigation.state.params.url, followersUrl:'', followingsUrl: '', reposUrl: ''}
  }

  componentWillMount(){

  	axios.get(this.state.url).then(response =>{
  		//console.warn(response)
  		// {params: {access_token: 'da56564fc9f431f0d09653aba249f4e1e1056221'}}
  		this.setState({  			
  			isLoading: false,
  			name: response.data.name,
        username: response.data.login,
        createDate: response.data.created_at,
        bio: response.data.bio,	
        followersUrl: response.data.followers_url,
        reposUrl: response.data.repos_url,
        avatarUrl: response.data.avatar_url,
      });
  	})
     
  }

	static navigationOptions = {
			title: 'Github Profile',
	};
	render(){
		var {params} = this.props.navigation.state;
		
		if(this.state.isLoading){
      return(
        <View style={{flex: 1, padding: 20}}>
          <Text> Loading github profile...</Text>
        </View>
      )
    }

		 
		return(
			
			<ImageBackground
					source = {require('./app/img/background2.jpg')}
					style = {styles.bgContainer}>
					<View style={styles.container}>

						<Image source={{uri:this.state.avatarUrl}} style={{width: 193, height: 170}}/> 
						<View style = {{justifyContent: 'center'}}>
							<Text> Name: {this.state.name} </Text>
							<Text> Username: {this.state.username} </Text>
							<Text> Bio: {this.state.bio} </Text>
							<Text> Created at: {this.state.createDate}</Text>
							
						</View>
						<Button
								
								title="Repositories"
								color="#33C6FF"
								onPress={()=>this.props.navigation.navigate('Repo',{url: this.state.reposUrl})}
						/>			
						<Button
								
								title="Followers"
								color="#33C6FF"
								onPress={()=>this.props.navigation.navigate('Follower', {followers: this.state.followersUrl})}
						/>			
						<Button
								
								title="Followings"
								color="#33C6FF"
								onPress={()=>this.props.navigation.navigate('Following', {username: this.state.username})}
						/>			
					</View>
			</ImageBackground>
		);
	}
}

/*
 repoScreen display
*/
class repoScreen extends Component {
	constructor(props){
    super(props);
    this.state = {isLoading: true, repos: [], reposUrl: this.props.navigation.state.params.url}
  }
	componentWillMount(){
		axios.get(this.state.reposUrl).then(response=>{
			//console.warn(response.data)
			this.setState({
				repos: response.data,
				isLoading: false
			});
		})
	}
	static navigationOptions = {
			title: 'My repositories',
	};
	render(){
		var { navigate } = this.props.navigation;
		if(this.state.isLoading){
      return(
        <View style={{flex: 1, padding: 20}}>
          <Text> Loading repositories...</Text>
        </View>
      )
    }
		return(
			<List>
				<FlatList
  					data={this.state.repos}
  					keyExtractor={item => item.name}
  					renderItem={({item}) => (
  						<ListItem onPress={ ()=> Linking.openURL(item.html_url)}
  							roundAvatar
  							title = {item.name}
  							subtitle = {item.owner.login}
  							description = {item.description}
  							avatar = {{uri: item.owner.avatar_url}}
  						/>
  					)}
				/>
			</List>
		);
	}
}

/*
 followingScreen display
*/
class followingScreen extends Component {
	constructor(props){
    super(props);
    this.state = {isLoading: true, followings: [], username: this.props.navigation.state.params.username}
  }
	componentWillMount(){
		axios.get('https://api.github.com/users/'+this.state.username+'/following').then(response=>{
			//console.warn(response.data)
			this.setState({
				followings: response.data,
				isLoading: false
			});
		})
	}

	static navigationOptions = {
			title: 'Following',
	};
	render(){
		const { navigate } = this.props.navigation;
		
		if(this.state.isLoading){
      return(
        <View style={{flex: 1, padding: 20}}>
          <Text> Loading followings...</Text>
        </View>
      )
    }

		return(
			<List>
				<FlatList
  					data={this.state.followings}
  					keyExtractor={item => item.login}
  					renderItem={({item}) => (
  						<ListItem onPress={()=>this.props.navigation.navigate('CHome', {url: item.url})}
  							roundAvatar
  							title = {item.login}
  							avatar = {{uri: item.avatar_url}}
  						/>
  					)}
				/>
			</List>
		);
	}
}

/*
 followerScreen display
*/
class followerScreen extends Component {

	constructor(props){
    super(props);
    this.state = {isLoading: true, followers: [], followersUrl: this.props.navigation.state.params.followers}
  }

	componentWillMount(){
		axios.get(this.state.followersUrl).then(response=>{
			//console.warn(response.data)
			this.setState({
				followers: response.data,
				isLoading: false
			});
		})
	}

	static navigationOptions = {
			title: 'Follower',
	};
	render(){
		const { navigate } = this.props.navigation;
		

		if(this.state.isLoading){
      return(
        <View style={{flex: 1, padding: 20}}>
          <Text> Loading followers...</Text>
        </View>
      )
    }

    return(
			<List>
				<FlatList
  					data={this.state.followers}
  					keyExtractor={item => item.login}
  					renderItem={({item}) => (
  						<ListItem  onPress={()=>this.props.navigation.navigate('CHome', {url: item.url})}
  							roundAvatar
  							title = {item.login}
  							avatar = {{uri: item.avatar_url}}
  						/>
  					)}
				/>
			</List>

		);
	}
}

/*
 dataScreen display
*/

class dataScreen extends Component{
	constructor(props){

    super(props);
    this.state = {
    	followings: [], 
    	followers: [], 
    	repos: [], 
    	bio: this.props.navigation.state.params.bio,
    	date: this.props.navigation.state.params.date,
    	name: this.props.navigation.state.params.name,
    	followersUrl: this.props.navigation.state.params.followers, 
    	reposUrl: this.props.navigation.state.params.url, 
    	username: this.props.navigation.state.params.username
    }
  }

  componentWillMount(){
  	
		axios.get(this.state.followersUrl, {params: {access_token: 'da56564fc9f431f0d09653aba249f4e1e1056221'}}).then(response=>{

			this.setState({
				followers: response.data,
				
			});
			
		})

		axios.get(this.state.reposUrl, {params: {access_token: 'da56564fc9f431f0d09653aba249f4e1e1056221'}}).then(response=>{
			
			this.setState({
				repos: response.data,
				
			});
		})

		axios.get('https://api.github.com/users/'+this.state.username+'/following', {params: {access_token: 'da56564fc9f431f0d09653aba249f4e1e1056221'}}).then(response=>{
			//console.warn(response.data)
			this.setState({
				followings: response.data,
				
			});
		})
	}

	static navigationOptions = {
		title: 'Save data locally'
	};

	render(){
		var{params} = this.props.navigation;
		return(

			<View style={styles.container}>
				<TouchableOpacity  onPress = {this.saveData()}>
					<Text style={styles.bodyText}> SAVE DATA </Text>
				</TouchableOpacity>

				<TouchableOpacity onPress = {this.displayProfile}>
					<Text style={styles.bodyText}> DISPLAY PROFILE </Text>
				</TouchableOpacity>

				<TouchableOpacity onPress = {this.displayFollower}>
					<Text style={styles.bodyText}> DISPLAY FOLLOWER </Text>
				</TouchableOpacity>

				<TouchableOpacity onPress = {this.displayFollowing}>
					<Text style={styles.bodyText}> DISPLAY FOLLOWING </Text>
				</TouchableOpacity>

				<TouchableOpacity onPress = {this.displayRepo}>
					<Text style={styles.bodyText}> DISPLAY REPO </Text>
				</TouchableOpacity>
			</View>
		);
	}

	saveData(){
		/*let obj = {
			name: {this.state.name},
			bio: this.state.bio,
			createdAt: this.state.date,
			username: this.state.username
		}*/
		AsyncStorage.clear()
		AsyncStorage.setItem('name', this.state.name);
		AsyncStorage.setItem('bio', this.state.bio);
		AsyncStorage.setItem('date', this.state.date);
		AsyncStorage.setItem('username', this.state.username);
		AsyncStorage.setItem('followers', JSON.stringify(this.state.followers));
		AsyncStorage.setItem('repos',  JSON.stringify(this.state.repos));
		AsyncStorage.setItem('followings',  JSON.stringify(this.state.followings));

	}

	
	displayProfile = async() => {
		try{
			let name = await AsyncStorage.getItem('name');
			let bio = await AsyncStorage.getItem('bio');
			let date = await AsyncStorage.getItem('date');
			let username = await AsyncStorage.getItem('username');
			alert(name + bio + date + username);
		}
		catch(error){
			alert(error);
		}
	}

	displayFollower = async() => {
		try{
			let followers = await AsyncStorage.getItem('followers');
			let parsed = JSON.parse(followers)
			console.warn(this.state.followers);
			
		}
		catch(error){
			alert(error);
		}
	}

	displayFollowing = async() => {
		try{
			let followings = await AsyncStorage.getItem('followings');
			let parsed = JSON.parse(followings)
			console.warn(this.state.followings);

		}
		catch(error){
			alert(error);
		}
	}

	displayRepo= async() => {
		try{
			let repos = await AsyncStorage.getItem('repos');
			let parsed = JSON.parse(repos)
			console.warn(this.state.repos);
		}
		catch(error){
			alert(error);
		}
	}

}
 
/*
 Screens intitializers
*/
const NavigationApp = StackNavigator({
	Home: {screen: homeScreen,},
	CHome: {screen: childHomeScreen,},	
	Repo: {screen: repoScreen,},
	Data: {screen: dataScreen,},
	Following: {screen: followingScreen,},
	Follower: {screen: followerScreen,},
	},
	{
		initialRouteName: 'Home'
});


/*
 Main
*/
export default class Avatar extends Component {
  render() {
     return <NavigationApp />;
  }
}

/*
 Stylesheets initializers
*/

const styles = StyleSheet.create({
	container:{
		flex: 1,
		alignItems: 'center',
		justifyContent: 'space-evenly',
	},

	bgContainer:{
		flex: 1,
		width: '100%',
		height: '100%',
	},
	repoContainer: {
   flex: 1,
   paddingTop: 22
  },

  bodyText:{
  	fontSize: 20,
  	fontWeight: 'bold',
  	fontFamily: 'Cochin',
  },
  item: {
    padding: 10,
    fontSize: 18,
    height: 44,
  },
});



 


