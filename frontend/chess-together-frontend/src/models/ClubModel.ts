import EventModel from "./EventModel";
import UserModel from "./UserModel";

class ClubModel{
    id?:number;
    title?:string;
    photoUrl?:string;
    content?:string;
    city?:string;
    createdBy?:UserModel;
    createdOn?:string;
    updatedOn?:string;
    events?:EventModel[]

    constructor(id: number, title: string, photoUrl: string, content: string, city: string,
                createdBy: UserModel, createdOn: string, updatedOn: string, events: EventModel[]) {
        this.id = id;
        this.title = title;
        this.photoUrl = photoUrl;
        this.content = content;
        this.city = city;
        this.createdBy = createdBy;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
        this.events = events;
    }
}

export default ClubModel