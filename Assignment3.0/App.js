import React, { Component } from 'react';
import { StyleSheet, Image, Text, View, Button, ImageBackground, FlatList, List} from 'react-native';
import { StackNavigator } from 'react-navigation';
import api from './app/api';

class repoScreen extends Component {

	static navigationOptions = {
			title: 'My repositories',
	};
	render(){
		const { navigate } = this.props.navigation;
		return(
			<View style={styles.repoContainer}>
				<FlatList
          data={[
            {key: 'Repo-1'},
            {key: 'Repo-2'},
            {key: 'Repo-3'},
          ]}
          renderItem={
          	({item}) => <Text style={styles.item}>{item.key}</Text>
          }
         />
         
			</View>

		);
	}
}


class homeScreen extends Component {
	constructor(props){
    super(props);
    this.state = {isLoading: true, isLoadingRepo: true, username: '',name: '', createDate: '', bio: '', avatarUrl: ''}
  }

  componentDidMount(){
     api.getData().then((res) =>{
      this.setState({
        isLoading: false,
        name: res.name,
        username: res.login,
        createDate: res.created_at,
        bio: res.bio,
        avatarUrl: res.avatar_url
      }) 
     })
  }

	static navigationOptions = {
			title: 'Welcome to my Github Page!',
	};
	render(){
		const { navigate } = this.props.navigation;

		if(this.state.isLoading){
      return(
        <View style={{flex: 1, padding: 20}}>
          <Text> Loading github profile...</Text>
        </View>
      )
    }

		 
		return(
			
			<ImageBackground
					source = {require('./app/img/background.jpg')}
					style = {styles.bgContainer}>
					<View style={styles.container}>

						<Image source={{uri:'https://avatars1.githubusercontent.com/u/19900371?v=4'}} style={{width: 193, height: 170}}/> 
						<View style = {{justifyContent: 'center'}}>
							<Text> Name: {this.state.name} </Text>
							<Text> Username: {this.state.username} </Text>
							<Text> Email: aldosanjoto@gmail.com </Text>
							<Text> Bio: {this.state.bio} </Text>
							<Text> Created at: {this.state.createDate}</Text>
						</View>
						<Button
								
								title="Repositories"
								color="#841584"
								onPress={()=>this.props.navigation.navigate('Repo')}
						/>			
						<Button
								
								title="My Followers"
								color="#841584"
								onPress={()=>this.props.navigation.navigate('Follower')}
						/>			
						<Button
								
								title="My Following"
								color="#841584"
								onPress={()=>this.props.navigation.navigate('Following')}
						/>			
					</View>
			</ImageBackground>
		);
	}
}

class followingScreen extends Component {
	static navigationOptions = {
			title: 'Following',
	};
	render(){
		const { navigate } = this.props.navigation;
		return(
			<View style={styles.container}>
				<Text>
				These are my followings 
				</Text>	
				<Button
  					
  					title="Home"
  					color="#841584"
  					onPress={()=>this.props.navigation.navigate('Home')}
				/>			
			</View>

		);
	}
}

class followerScreen extends Component {
	static navigationOptions = {
			title: 'Follower',
	};
	render(){
		const { navigate } = this.props.navigation;
		return(
			<View style={styles.container}>
			<Text>
				These are my followers 
			</Text> 
			</View>

		);
	}
}

const NavigationApp = StackNavigator({
	Home: {screen: homeScreen,},	
	Repo: {screen: repoScreen,},
	Following: {screen: followingScreen,},
	Follower: {screen: followerScreen,},
	},
	{
		initialRouteName: 'Home'
});

export default class Avatar extends Component {
  render() {
     return <NavigationApp />;
  }
}


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
  item: {
    padding: 10,
    fontSize: 18,
    height: 44,
  },
});



 


