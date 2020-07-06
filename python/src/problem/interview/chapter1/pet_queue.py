
class Pet(object):
    p_type = ""
    count = 0

    def __init__(self, p_type, count):
        self.p_type = p_type
        self.count = count

class PetQueue(object):

    dog_queue, cat_queue = [], []
    count = 0

    def __init__(self):
        self.count = 0

    def add(self, p: Pet):
        """

        :param p:
        :return:
        """
        self.count += 1
        p.count = self.count
        if p.p_type == "dog":
            self.dog_queue.append(p)
        else:
            self.cat_queue.append(p)

    def poll_all(self) -> Pet:
        """

        :return:
        """
        if len(self.dog_queue) > 0 and len(self.cat_queue) > 0:
            if self.dog_queue[0].count < self.cat_queue[0].count:
                return self.dog_queue.pop(0)
            else:
                return self.cat_queue.pop(0)
        elif len(self.dog_queue) > 0:
            return self.dog_queue.pop(0)
        elif len(self.cat_queue) > 0:
            return self.cat_queue.pop(0)
        else:
            return None

    def poll_dog(self) -> Pet:
        """

        :return:
        """
        if len(self.dog_queue) > 0:
            return self.dog_queue.pop(0)
        return None

    def poll_cat(self) -> Pet:
        """

        :return:
        """
        if len(self.cat_queue) > 0:
            return self.cat_queue.pop(0)
        return None

    def is_empty(self) -> bool:
        """

        :return:
        """
        return len(self.cat_queue) == len(self.dog_queue) == 0

    def is_dog_queue_empty(self) -> bool:
        """

        :return:
        """
        return len(self.dog_queue) == 0

    def is_cat_queue_empty(self) -> bool:
        """

        :return:
        """
        return len(self.cat_queue) == 0

