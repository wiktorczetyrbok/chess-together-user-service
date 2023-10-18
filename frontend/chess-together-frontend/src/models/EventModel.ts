import ClubModel from './ClubModel'
import UserModel from './UserModel'
import {type} from "@testing-library/user-event/dist/type";
class EventModel {
    id: number;
    name: string;
    startTime: string;
    endTime: string;
    type: string;
    photoUrl: string;
    createdOn: string;
    updatedOn: string;
    club: ClubModel;
    assignedUsers: UserModel[];

    constructor(id:number, name: string, startTime: string, endTime: string, type: string, photoUrl: string,
                createdOn: string, updatedOn: string, club: ClubModel, assignedUsers: UserModel[]) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.type = type;
        this.photoUrl = photoUrl;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
        this.club = club;
        this.assignedUsers = assignedUsers;
    }
}
export default EventModel;
