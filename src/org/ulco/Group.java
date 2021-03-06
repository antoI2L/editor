package org.ulco;

public class Group extends GraphicsObjects {

    protected Group group;
    protected int m_ID;

    public Group() {
        group = null;
        m_ID = ID.instance().generate();
    }

    public void init(String json) {
        group = null;

        String str = json.replaceAll("\\s+", "");
        int objectsIndex = str.indexOf("objects");
        int groupsIndex = str.indexOf("groups");
        int endIndex = str.lastIndexOf("}");

        parseObjects(str.substring(objectsIndex + 9, groupsIndex - 2));
        parseGroups(str.substring(groupsIndex + 8, endIndex - 1));
    }

    public Group getGroup() {
        return group;
    }

    public void add(Group group) {
        if (null == this.group) {
            this.group = group;
        } else {
            this.group.add(group);
        }
    }

    public Group copy() {
        Group g = new Group();
        g.add(group);
        for (GraphicsObject o : this) {
            g.add(o);
        }
        return g;
    }

    public int getID() {
        return m_ID;
    }

    public void move(Point delta) {
        for (GraphicsObject o : this) {
            o.move(delta);
        }

        if (null != group) {
            group.move(delta);
        }
    }

    /*protected int searchSeparator(String str) {
        int index = 0;
        int level = 0;
        boolean found = false;

        while (!found && index < str.length()) {
            if (str.charAt(index) == '{') {
                ++level;
                ++index;
            } else if (str.charAt(index) == '}') {
                --level;
                ++index;
            } else if (str.charAt(index) == ',' && level == 0) {
                found = true;
            } else {
                ++index;
            }
        }
        if (found) {
            return index;
        } else {
            return -1;
        }
    }*/

    protected void parseGroups(String groupsStr) {
        while (!groupsStr.isEmpty()) {
            int separatorIndex = Util.searchSeparator(groupsStr);
            String groupStr;

            if (separatorIndex == -1) {
                groupStr = groupsStr;
            } else {
                groupStr = groupsStr.substring(0, separatorIndex);
            }

            if (null != group) {
                group.add(JSON.parseGroup(groupStr));
            } else {
                group = JSON.parseGroup(groupStr);
            }

            if (separatorIndex == -1) {
                groupsStr = "";
            } else {
                groupsStr = groupsStr.substring(separatorIndex + 1);
            }
        }
    }

    protected void parseObjects(String objectsStr) {
        while (!objectsStr.isEmpty()) {
            int separatorIndex = Util.searchSeparator(objectsStr);
            String objectStr;

            if (separatorIndex == -1) {
                objectStr = objectsStr;
            } else {
                objectStr = objectsStr.substring(0, separatorIndex);
            }
            add(JSON.parse(objectStr));
            if (separatorIndex == -1) {
                objectsStr = "";
            } else {
                objectsStr = objectsStr.substring(separatorIndex + 1);
            }
        }
    }

    public int size() {
        if (null != group) {
            return super.size() + group.size();
        }

        return super.size();
    }


    public String stringify(String debut,String milieu, String fin){

        String str = debut;

        for (int i = 0; i < super.size(); ++i) {
            GraphicsObject element = this.elementAt(i);

            str += element.toJson();
            if (i < super.size() - 1) {
                str += ", ";
            }
        }
        str += milieu;

        if (null != group) {
            str += group.toJson();
        }

        return str + fin;
    }


    public String toJson() {
        return stringify( "{ type: group, objects : { " ," }, groups : { "," } }");
    }

    public String toString() {
        return stringify("group[[","],[","]]");
    }
}
