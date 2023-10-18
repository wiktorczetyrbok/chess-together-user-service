import EventModel from "./EventModel";

class UserModel{

    id?:number;
    username?:string;
    email?:string;
    avatarUrl?:string;
    rating?:number;
    events?:EventModel[]


    constructor(id: number, username: string, email: string,
                avatarUrl: string, rating: number, events: EventModel[]) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.avatarUrl = avatarUrl;
        this.rating = rating;
        this.events = events;
    }
}

export default UserModel